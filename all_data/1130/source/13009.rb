#Matt Hall
#Homework 1-3

#3a
def combine_anagrams(words)
  output = Array.new
  words2 = words.map { |word| word.downcase.each_char.to_a.sort.join }
  words2.uniq.each do |sorted|
    tmp = Array.new
    words.each do |orig|
      if sorted == orig.downcase.each_char.to_a.sort.join
        tmp.push orig
      end
    end
    output.push tmp unless tmp.empty?
  end
  output
end