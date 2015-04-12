class Phrase
  def initialize(str)
    @words = str
  end

  def word_count
    ans = {}
    arr = @words.scan(/\w+[']?\w+/)
    @words.scan(/[\d+]/).each {|x| arr << x}
    arr.map! {|word| word.downcase}
    arr.each do |word|
      next if word.empty?
      ans[word] = arr.count(word)
    end

    return ans
  end
end
