class Phrase
  def initialize(string)
    @string = string.downcase
  end

  def word_count
    words = @string.split(/\W+/)
    report = {}
    words.each do |word|
      report[word] ||= 0
      report[word] += 1
    end
    report
  end

end
