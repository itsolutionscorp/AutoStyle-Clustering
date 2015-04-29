def combine_anagrams (words)
  hsh = Hash.new
  ary = Array.new
  words.each do |word|
    chararray = Array.new
    word.downcase.each_char { |c| chararray << c }
    if hsh.has_key?(chararray.sort)
      hsh[chararray.sort] << word
    else
      hsh[chararray.sort] = Array[word]
    end
  end
  hsh.each { |key, value| ary << value }
  ary
end

