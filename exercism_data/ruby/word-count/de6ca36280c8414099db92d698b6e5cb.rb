class Words
  attr_reader :words

  def initialize(words)
    @words = words.downcase.gsub(/[^a-z0-9 ]/,"")
  end

  def count
    answer = {}
    words.split(" ").each do |word|
      if answer[word]
        answer[word] += 1
      else
        answer[word] = 1
      end
    end
    answer
  end
end
