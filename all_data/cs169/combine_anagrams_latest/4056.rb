def combine_anagrams(words)
  @anagarry = Array.new
  @anahash = Hash.new(0)
  words.map do |word| 
    if @anahash.has_key?(word.downcase.split('').sort.join)
      @anahash[word.downcase.split('').sort.join].push(word)
    else
      @anahash[word.downcase.split('').sort.join] = [word]
    end
  end
  @anahash.each_value{|value| @anagarry.push(value)}
  return @anagarry
end