def combine_anagrams(words)
  output = Array.new(0)
  words.each do |w1|
    temp_array = []
    words.each do |w2|
      if (w2.downcase.split(//).sort == w1.downcase.split(//).sort)
        temp_array.push(w2)
      end
    end
    output.push(temp_array)
  end
  output.uniq
end

if __FILE__ == $0

  puts(combine_anagrams(%w(cars for potatoes racs four scar creams scream)).to_s)

end