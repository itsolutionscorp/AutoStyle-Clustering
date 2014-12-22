def combine_anagrams(word_list)
  words=Hash.new{|h,k| h[k]=Array.new};
  word_list.each do |word|
    word_down=word.to_s.downcase;
    word_chars=word_down.split(//);
    key_word=word_chars.sort().to_s;
    words[key_word] << word;
  end
 
 i=0;
 output=[]
 words.each {|key, value|  output[i]=value;i+=1;}
 return output;
end

