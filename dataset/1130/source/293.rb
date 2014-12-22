def combine_anagrams(words)
  output_ary = Array.new
  sorted_words_ary = Array.new
  words.each do |w|
    word_ary = Array.new
    sorted_word = String.new

    w.downcase.each_char do |c|
      word_ary << c
    end

    word_ary.sort!

    word_ary.each do |s|
      sorted_word += s
    end

    sorted_words_ary << sorted_word
  end

  anagram_pos_hash = Hash.new
  output_ary_index = 0

  0.upto sorted_words_ary.length-1 do |i|
    cur_anagram = sorted_words_ary[i]
    cur_word = words[i]
    match_pos = sorted_words_ary.index(cur_anagram)
    if match_pos == i # this anagram's first occurance
      output_ary << Array(cur_word)
      anagram_pos_hash[cur_anagram] = output_ary_index
      output_ary_index += 1
    else # this anagram occured before
      output_ary_pos = anagram_pos_hash[cur_anagram]
      output_ary[output_ary_pos] << cur_word
    end
  end
  output_ary
end
