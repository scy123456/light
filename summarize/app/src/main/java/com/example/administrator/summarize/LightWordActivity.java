package com.example.administrator.summarize;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LightWordActivity extends Activity {

    private List<String> mAllSpecicalWords;
    private Context mContext;
    private ImageView change;
    private TextView light_final;
    private EditText light_sentance,light_word;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        mContext = getApplicationContext();
        mAllSpecicalWords = new ArrayList<>();

        light_word = (EditText)findViewById(R.id.light_word);
        light_sentance = (EditText)findViewById(R.id.light_sentance);
        light_final = (TextView)findViewById(R.id.light_final);
        change = (ImageView)findViewById(R.id.change);
//        light_word = (EditText)findViewById(R.id.light_word);
        addSpecialWords();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeWordType(light_sentance.getText().toString(),light_word.getText().toString());
            }
        });
    }



    public void changeWordType(String sentance, String mDemoWord) {
        int spaceCount = 0;
        SpannableStringBuilder builder = new SpannableStringBuilder(sentance);
        int indexTemp1 = sentance.toLowerCase().indexOf(mDemoWord.toLowerCase());
        int indexTemp2 = 0;
        /**
         * 如果是一个特殊格式的单词，则进行重组或者选择
         */
        if (indexTemp1 == -1) {
            String wordTemp = "";
            //如果结尾为y，先加ies检测，不符合则加ied检测

            if (mDemoWord.toLowerCase().charAt(mDemoWord.length() - 1) == 'y') {
                wordTemp = mDemoWord.substring(0, mDemoWord.length() - 1) + "ies";
                indexTemp1 = sentance.toLowerCase().indexOf(wordTemp);
                if (indexTemp1 == -1) {
                    wordTemp = mDemoWord.substring(0, mDemoWord.length() - 1) + "ied";
                    indexTemp1 = sentance.toLowerCase().indexOf(wordTemp);
                    if (indexTemp1 > -1) {
                        for (int i = indexTemp1; indexTemp1 < sentance.length(); i++) {
                            if (i == sentance.length() - 1) {
                                indexTemp2 = i;
                                break;
                            }
                            if (sentance.toLowerCase().charAt(i) > 'z' || sentance.toLowerCase().charAt(i) < 'a') {
                                indexTemp2 = i;
                                break;
                            }
                        }
                    }
                } else {
                    for (int i = indexTemp1; indexTemp1 < sentance.length(); i++) {
                        if (i == sentance.length() - 1) {
                            indexTemp2 = i;
                            break;
                        }
                        if (sentance.toLowerCase().charAt(i) > 'z' || sentance.toLowerCase().charAt(i) < 'a') {
                            indexTemp2 = i;
                            break;
                        }
                    }
                }
                //如果结尾为f，先加ies检测，不符合则加ied检测
            } else if (mDemoWord.toLowerCase().charAt(mDemoWord.length() - 1) == 'f') {
                wordTemp = mDemoWord.substring(0, mDemoWord.length() - 1) + "ves";
                indexTemp1 = sentance.toLowerCase().indexOf(wordTemp);
                if (indexTemp1 > -1) {
                    for (int i = indexTemp1; indexTemp1 < sentance.length(); i++) {
                        if (i == sentance.length() - 1) {
                            indexTemp2 = i;
                            break;
                        }
                        if (sentance.toLowerCase().charAt(i) > 'z' || sentance.toLowerCase().charAt(i) < 'a') {
                            indexTemp2 = i;
                            break;
                        }
                    }
                }
            } else if (mDemoWord.toLowerCase().substring(mDemoWord.length() - 2, mDemoWord.length()).equals("fe")) {
                wordTemp = mDemoWord.substring(0, mDemoWord.length() - 2) + "ves";
                indexTemp1 = sentance.toLowerCase().indexOf(wordTemp);
                if (indexTemp1 > -1) {
                    for (int i = indexTemp1; indexTemp1 < sentance.length(); i++) {
                        if (i == sentance.length() - 1) {
                            indexTemp2 = i;
                            break;
                        }
                        if (sentance.toLowerCase().charAt(i) > 'z' || sentance.toLowerCase().charAt(i) < 'a') {
                            indexTemp2 = i;
                            break;
                        }
                    }
                }
            }

            //如果结尾不符合要求且未被检测，则为特殊格式

            if (mAllSpecicalWords.contains(mDemoWord)) {
                String wordsTemp = selectSpecialWord(mDemoWord, sentance);
                indexTemp1 = sentance.toLowerCase().indexOf(wordsTemp);
                if (indexTemp1 > -1) {
                    for (int i = indexTemp1; indexTemp1 < sentance.length(); i++) {
                        if (i == sentance.length() - 1) {
                            indexTemp2 = i;
                            break;
                        }
                        if (sentance.toLowerCase().charAt(i) > 'z' || sentance.toLowerCase().charAt(i) < 'a') {
                            indexTemp2 = i;
                            break;
                        }
                    }
                }
            }


        } else {
            /**
             * baoha
             */
            if (mDemoWord.indexOf(" ") > -1) {
                for (int i = 0; i < mDemoWord.length() - 1; i++) {
                    if (mDemoWord.substring(i, i + 1).equals(" ")) {
                        spaceCount++;
                    }
                }

                for (int i = indexTemp1; indexTemp1 < sentance.length(); i++) {
                    if (i == sentance.length() - 1) {
                        indexTemp2 = i;
                        break;
                    }
                    if (sentance.toLowerCase().charAt(i) > 'z' || sentance.toLowerCase().charAt(i) < 'a') {
                        if (spaceCount == 0) {
                            indexTemp2 = i;
                            break;
                        }
                        spaceCount--;
                    }
                }
                /**
                 * 正常格式直接检测即可
                 */
            } else {
                for (int i = indexTemp1; indexTemp1 < sentance.length(); i++) {
                    if (i == sentance.length() - 1) {
                        indexTemp2 = i;
                        break;
                    }
                    if (sentance.toLowerCase().charAt(i) > 'z' || sentance.toLowerCase().charAt(i) < 'a') {
                        indexTemp2 = i;
                        break;
                    }
                }
            }
        }

        if (indexTemp1 < 0) {
            return;
        }
        builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.blue)), indexTemp1, indexTemp2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        light_final.setText(builder);
    }

    private String selectSpecialWord(String specialWord, String sentance) {
        int indexTemp = -1;
        String returnString = "";
        switch (specialWord) {
            case "drink":
                indexTemp = sentance.toLowerCase().indexOf("drunk");
                returnString = "drunk";
                break;
            case "stand":
                indexTemp = sentance.toLowerCase().indexOf("stood");
                returnString = "stood";
                break;
            case "spend":
                indexTemp = sentance.toLowerCase().indexOf("spent");
                returnString = "spent";
                break;
            case "bend":
                indexTemp = sentance.toLowerCase().indexOf("bent");
                returnString = "bent";
                break;
            case "know":
                indexTemp = sentance.toLowerCase().indexOf("knew");
                returnString = "knew";
                break;
            case "lie":
                indexTemp = sentance.toLowerCase().indexOf("lay");
                returnString = "lay";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("lied");
                    returnString = "lied";
                }
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("lain");
                    returnString = "lain";
                }
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("laid");
                    returnString = "laid";
                }
                break;
            case "hide":
                indexTemp = sentance.toLowerCase().indexOf("hidden");
                returnString = "hidden";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("hid");
                    returnString = "hid";
                }
                break;
            case "dig":
                indexTemp = sentance.toLowerCase().indexOf("dug");
                returnString = "dug";
                break;
            case "hang":
                indexTemp = sentance.toLowerCase().indexOf("hung");
                returnString = "hung";
                break;
            case "hold":
                indexTemp = sentance.toLowerCase().indexOf("hung");
                returnString = "hung";
                break;
            case "shine":
                indexTemp = sentance.toLowerCase().indexOf("shone");
                returnString = "shone";
                break;
            case "win":
                indexTemp = sentance.toLowerCase().indexOf("won");
                returnString = "won";
                break;
            case "keep":
                indexTemp = sentance.toLowerCase().indexOf("kept");
                returnString = "kept";
                break;
            case "sleep":
                indexTemp = sentance.toLowerCase().indexOf("slept");
                returnString = "slept";
                break;

            case "smell":
                indexTemp = sentance.toLowerCase().indexOf("smelt");
                returnString = "smelt";
                break;
            case "lose":
                indexTemp = sentance.toLowerCase().indexOf("lost");
                returnString = "lost";
                break;
            case "break":
                indexTemp = sentance.toLowerCase().indexOf("broke");
                returnString = "broke";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("broken");
                    returnString = "broken";
                }
                break;
            case "choose":
                indexTemp = sentance.toLowerCase().indexOf("chose");
                returnString = "chose";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("chosen");
                    returnString = "chosen";
                }
                break;
            case "forget":
                indexTemp = sentance.toLowerCase().indexOf("forgot");
                returnString = "forgot";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("forgotten");
                    returnString = "forgotten";
                }
                break;

            case "lay":
                indexTemp = sentance.toLowerCase().indexOf("lie");
                returnString = "lie";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("lain");
                    returnString = "lain";
                }
                break;
            case "ride":
                indexTemp = sentance.toLowerCase().indexOf("rode");
                returnString = "rode";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("ridden");
                    returnString = "ridden";
                }
                break;
            case "mistake":
                indexTemp = sentance.toLowerCase().indexOf("mistook");
                returnString = "mistook";
                break;
            case "rise":
                indexTemp = sentance.toLowerCase().indexOf("rose");
                returnString = "rose";
                break;
            case "give":
                indexTemp = sentance.toLowerCase().indexOf("gave");
                returnString = "gave";
                break;
            case "wake":
                indexTemp = sentance.toLowerCase().indexOf("woke");
                returnString = "woke";
                break;
            case "blow":
                indexTemp = sentance.toLowerCase().indexOf("blew");
                returnString = "blew";
                break;
            case "understand":
                indexTemp = sentance.toLowerCase().indexOf("understood");
                returnString = "understood";
                break;
            case "do":
                indexTemp = sentance.toLowerCase().indexOf("did");
                returnString = "did";
                break;
            case "speak":
                indexTemp = sentance.toLowerCase().indexOf("spoken");
                returnString = "spoken";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("spoke");
                    returnString = "spoke";
                }
                break;
            case "meet":
                indexTemp = sentance.toLowerCase().indexOf("met");
                returnString = "met";
                break;
            case "tell":
                indexTemp = sentance.toLowerCase().indexOf("told");
                returnString = "told";
                break;
            case "feel":
                indexTemp = sentance.toLowerCase().indexOf("felt");
                returnString = "felt";
                break;
            case "wear":
                indexTemp = sentance.toLowerCase().indexOf("wore");
                returnString = "wore";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("worn");
                    returnString = "worn";
                }
                break;
            case "sell":
                indexTemp = sentance.toLowerCase().indexOf("sold");
                returnString = "sold";
                break;
            case "send":
                indexTemp = sentance.toLowerCase().indexOf("sent");
                returnString = "sent";
                break;
            case "lend":
                indexTemp = sentance.toLowerCase().indexOf("lent");
                returnString = "lent";
                break;
            case "build":
                indexTemp = sentance.toLowerCase().indexOf("built");
                returnString = "built";
                break;
            case "leave":
                indexTemp = sentance.toLowerCase().indexOf("left");
                returnString = "left";
                break;
            case "buy":
                indexTemp = sentance.toLowerCase().indexOf("bought");
                returnString = "bought";
                break;
            case "see":
                indexTemp = sentance.toLowerCase().indexOf("saw");
                returnString = "saw";
                break;
            case "fight":
                indexTemp = sentance.toLowerCase().indexOf("fought");
                returnString = "fought";
                break;
            case "find":
                indexTemp = sentance.toLowerCase().indexOf("found");
                returnString = "found";
                break;
            case "sweep":
                indexTemp = sentance.toLowerCase().indexOf("swept");
                returnString = "swept";
                break;

            case "begin":
                indexTemp = sentance.toLowerCase().indexOf("began");
                returnString = "began";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("begun");
                    returnString = "begun";
                }
                break;
            case "grow":
                indexTemp = sentance.toLowerCase().indexOf("grew");
                returnString = "grew";
                break;
            case "swim":
                indexTemp = sentance.toLowerCase().indexOf("swam");
                returnString = "swam";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("swum");
                    returnString = "swum";
                }
                break;
            case "ring":
                indexTemp = sentance.toLowerCase().indexOf("rang");
                returnString = "rang";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("rung");
                    returnString = "rung";
                }
                break;
            case "write":
                indexTemp = sentance.toLowerCase().indexOf("wrote");
                returnString = "wrote";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("written");
                    returnString = "written";
                }
                break;
            case "have":
                indexTemp = sentance.toLowerCase().indexOf("has");
                returnString = "has";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("had");
                    returnString = "had";
                }
                break;
            case "may":
                indexTemp = sentance.toLowerCase().indexOf("might");
                returnString = "might";
                break;
            case "can":
                indexTemp = sentance.toLowerCase().indexOf("could");
                returnString = "could";
                break;
            case "shall":
                indexTemp = sentance.toLowerCase().indexOf("should");
                returnString = "should";
                break;
            case "run":
                indexTemp = sentance.toLowerCase().indexOf("ran");
                returnString = "ran";
                break;
            case "will":
                indexTemp = sentance.toLowerCase().indexOf("would");
                returnString = "would";
                break;
            case "eat":
                indexTemp = sentance.toLowerCase().indexOf("ate");
                returnString = "ate";
                break;
            case "drive":
                indexTemp = sentance.toLowerCase().indexOf("drove");
                returnString = "drove";
                break;
            case "sing":
                indexTemp = sentance.toLowerCase().indexOf("sang");
                returnString = "sang";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("sung");
                    returnString = "sung";
                }
                break;
            case "draw":
                indexTemp = sentance.toLowerCase().indexOf("drew");
                returnString = "drew";
                break;
            case "man":
                indexTemp = sentance.toLowerCase().indexOf("men");
                returnString = "men";
                break;
            case "woman":
                indexTemp = sentance.toLowerCase().indexOf("women");
                returnString = "women";
                break;
            case "tooth":
                indexTemp = sentance.toLowerCase().indexOf("teeth");
                returnString = "teeth";
                break;
            case "child":
                indexTemp = sentance.toLowerCase().indexOf("children");
                returnString = "children";
                break;
            case "goose":
                indexTemp = sentance.toLowerCase().indexOf("geese");
                returnString = "geese";
                break;
            case "go":
                indexTemp = sentance.toLowerCase().indexOf("went");
                returnString = "went";
                break;
            case "make":
                indexTemp = sentance.toLowerCase().indexOf("made");
                returnString = "made";
                break;
            case "get":
                indexTemp = sentance.toLowerCase().indexOf("got");
                returnString = "got";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("gotten");
                    returnString = "gotten";
                }
                break;
            case "come":
                indexTemp = sentance.toLowerCase().indexOf("came");
                returnString = "came";
                break;
            case "become":
                indexTemp = sentance.toLowerCase().indexOf("became");
                returnString = "became";
                break;
            case "catch":
                indexTemp = sentance.toLowerCase().indexOf("caught");
                returnString = "caught";
                break;
            case "sit":
                indexTemp = sentance.toLowerCase().indexOf("sat");
                returnString = "sat";
                break;
            case "take":
                indexTemp = sentance.toLowerCase().indexOf("took");
                returnString = "took";
                break;
            case "say":
                indexTemp = sentance.toLowerCase().indexOf("said");
                returnString = "said";
                break;
            case "think":
                indexTemp = sentance.toLowerCase().indexOf("thought");
                returnString = "thought";
                break;
            case "tear":
                indexTemp = sentance.toLowerCase().indexOf("tore");
                returnString = "tore";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("torn");
                    returnString = "torn";
                }
                break;
            case "teach":
                indexTemp = sentance.toLowerCase().indexOf("taught");
                returnString = "taught";
                break;
            case "bring":
                indexTemp = sentance.toLowerCase().indexOf("brought");
                returnString = "brought";
                break;
            case "are":
                indexTemp = sentance.toLowerCase().indexOf("were");
                returnString = "were";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("been");
                    returnString = "been";
                }
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("be");
                    returnString = "be";
                }
                break;
            case "am":
                indexTemp = sentance.toLowerCase().indexOf("was");
                returnString = "was";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("been");
                    returnString = "been";
                }
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("be");
                    returnString = "be";
                }
                break;
            case "is":
                indexTemp = sentance.toLowerCase().indexOf("was");
                returnString = "was";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("been");
                    returnString = "been";
                }
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("be");
                    returnString = "be";
                }
                break;
            case "fly":
                indexTemp = sentance.toLowerCase().indexOf("flew");
                returnString = "flew";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("flown");
                    returnString = "flown";
                }
                break;
            case "fall":
                indexTemp = sentance.toLowerCase().indexOf("fell");
                returnString = "flew";
                if (indexTemp == -1) {
                    indexTemp = sentance.toLowerCase().indexOf("flown");
                    returnString = "flown";
                }
                break;
        }
        return returnString;
    }

    public void addSpecialWords() {
        mAllSpecicalWords.add("drink");//drank drunk
        mAllSpecicalWords.add("give");
        mAllSpecicalWords.add("stand");
        mAllSpecicalWords.add("win");
        mAllSpecicalWords.add("spend");//spent
        mAllSpecicalWords.add("bend");
        mAllSpecicalWords.add("meet");
        mAllSpecicalWords.add("know");
        mAllSpecicalWords.add("blow");
        mAllSpecicalWords.add("draw");//drew
        mAllSpecicalWords.add("lie");
        mAllSpecicalWords.add("can");
        mAllSpecicalWords.add("hide");
        mAllSpecicalWords.add("dig");//dug
        mAllSpecicalWords.add("hang");//hung
        mAllSpecicalWords.add("shine");//shone
        mAllSpecicalWords.add("win");
        mAllSpecicalWords.add("keep");
        mAllSpecicalWords.add("smell");//smelt
        mAllSpecicalWords.add("lose");//lost
        mAllSpecicalWords.add("break");//broke broken
        mAllSpecicalWords.add("choose");
        mAllSpecicalWords.add("forget");//forgot forgotten
        mAllSpecicalWords.add("lay");//lie  lain
        mAllSpecicalWords.add("wear");//wore worn
        mAllSpecicalWords.add("ride");//rode ridden
        mAllSpecicalWords.add("mistake");//mistook
        mAllSpecicalWords.add("rise");//rose
        mAllSpecicalWords.add("wake");//woke
        mAllSpecicalWords.add("understand");//understood
        mAllSpecicalWords.add("sell");//sold
        mAllSpecicalWords.add("send");//sent
        mAllSpecicalWords.add("lend");//lent
        mAllSpecicalWords.add("build");//built
        mAllSpecicalWords.add("leave");//left
        mAllSpecicalWords.add("see");//saw
        mAllSpecicalWords.add("sleep");
        mAllSpecicalWords.add("fight");
        mAllSpecicalWords.add("find");
        mAllSpecicalWords.add("sweep");
        mAllSpecicalWords.add("hold");
        mAllSpecicalWords.add("feel");
        mAllSpecicalWords.add("tell");//told
        mAllSpecicalWords.add("speak");//spoken spoke
        mAllSpecicalWords.add("begin");//began begun
        mAllSpecicalWords.add("grow");//grow（生长） grew grown
        mAllSpecicalWords.add("have");
        mAllSpecicalWords.add("swim");//swan swum
        mAllSpecicalWords.add("may");
        mAllSpecicalWords.add("ring");//rang rung
        mAllSpecicalWords.add("shall");
        mAllSpecicalWords.add("run");
        mAllSpecicalWords.add("will");
        mAllSpecicalWords.add("write");//wrote written
        mAllSpecicalWords.add("eat");
        mAllSpecicalWords.add("drive");
        mAllSpecicalWords.add("cut");
        mAllSpecicalWords.add("sing");//sang sung
        mAllSpecicalWords.add("man");
        mAllSpecicalWords.add("woman");
        mAllSpecicalWords.add("tooth");
        mAllSpecicalWords.add("child");
        mAllSpecicalWords.add("goose");
        mAllSpecicalWords.add("go");
        mAllSpecicalWords.add("make");
        mAllSpecicalWords.add("buy");
        mAllSpecicalWords.add("get");
        mAllSpecicalWords.add("come");
        mAllSpecicalWords.add("fly");//flew flown
        mAllSpecicalWords.add("is");
        mAllSpecicalWords.add("am");
        mAllSpecicalWords.add("are");
        mAllSpecicalWords.add("bring");
        mAllSpecicalWords.add("do");
        mAllSpecicalWords.add("teach");
        mAllSpecicalWords.add("think");
        mAllSpecicalWords.add("say");
        mAllSpecicalWords.add("sit");
        mAllSpecicalWords.add("take");
        mAllSpecicalWords.add("catch");
        mAllSpecicalWords.add("become");
        mAllSpecicalWords.add("tear");
        mAllSpecicalWords.add("fall");
    }
}
