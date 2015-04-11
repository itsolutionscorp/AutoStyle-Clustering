class PigLatin

  def self.translate(words)
    word_arr = words.split(" ").to_a
    translated_words = []
    word_arr.each do |word| 
      if /[aeiou]/.match(word[0]) || "yt" == word[0..1] || "xr" == word[0..1]
        translated_words << word+"ay" 
      elsif "thr" == word[0..2] || "sch" == word[0..2] || /^.{1}qu.+/.match(word) 
        translated_words << word[3..-1]+word[0..2]+"ay" 
      elsif "ch" == word[0..1] || "qu" == word[0..1] || "th" == word[0..1]  
        translated_words << word[2..-1]+word[0..1]+"ay" 
      else  
        translated_words << word[1..-1]+word[0]+"ay"
      end
    end  
    return translated_words.join(" ")
  end

end
