class Phrase

  def initialize(line)
    @words = line.downcase.gsub(',',' ').tr('^0-9A-Za-z ', '').split(" ")
  end

  def word_count
    hash = {}
    @words.each do |word|
      hash[word] ? hash[word] += 1 : hash[word] = 1
    end
    hash
  end

end
