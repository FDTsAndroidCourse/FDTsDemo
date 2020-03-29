/*
 * Copyright (c) 2020 fdt <frederic.dt.twh@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package onl.fdt.android.fdtsdemo.CyclerView.model;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PullParser {

    private static final Logger LOGGER = Logger.getLogger(PullParser.class.getName());

    /**
     * @param is inputStream
     * @return
     * @throws Exception
     */
    public static List<Message> pull2xml(InputStream is) {
        List<Message> list = new ArrayList<>();
        Message msg = null;
        //创建xmlPull解析器
        XmlPullParser parser = Xml.newPullParser();
        ///初始化xmlPull解析器
        try {
            parser.setInput(is, "utf-8");

            //读取文件的类型
            int type = 0;

            type = parser.getEventType();

            //无限判断文件类型进行读取
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    //开始标签
                    case XmlPullParser.START_TAG:
                        if ("messages".equals(parser.getName())) {
//                            list = new ArrayList<>();
                        } else if ("message".equals(parser.getName())) {
                            msg = new Message();
                        } else if ("title".equals(parser.getName())) {
                            //获取title属性
                            String isOfficial = parser.getAttributeValue(null, "isOfficial");
                            msg.setOfficial("true".equals(isOfficial));
                            String title = parser.nextText();
                            msg.setTitle(title);
                        } else if ("time".equals(parser.getName())) {
                            //time
                            String time = parser.nextText();
                            msg.setTime(time);
                        } else if ("hashtag".equals(parser.getName())) {
                            //hashTag
                            String hashTag = parser.nextText();
                            msg.setDescription(hashTag);
                        } else if ("icon".equals(parser.getName())) {
                            //icon
                            String icon = parser.nextText();
                            msg.setIcon(icon);
                        }
                        break;
                    //结束标签
                    case XmlPullParser.END_TAG:
                        if ("message".equals(parser.getName())) {
                            list.add(msg);
                        }
                        break;
                }
                //继续往下读取标签类型
                type = parser.next();
            }
        } catch (XmlPullParserException e) {
            LOGGER.warning("pull2xml() XmlPullParserException");
        } catch (IOException e) {
            LOGGER.warning("pull2xml() IOException");
        }
        return list;
    }
}
