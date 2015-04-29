def combine_anagrams(words)

  sorted_words = Hash.new;
  words.each do | word|
    lower = word.downcase;
    arr = Array.new;
    lower.each_char{ |c| arr << c}
    arr.sort!;
    str = String.new;
    arr.each { |c| str << c}
    if !sorted_words.has_key?(str) then
      sorted_words[str] = Array.new;     
    end  
    sorted_words[str] << word;   
  end
  
  return sorted_words.values();
  
end