class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    string = @string.gsub(/[^a-zA-Z\s,0-9']/, "").downcase.split(/[,\s]+/)
    words = {}
    string.each{|x|
      words[x] = string.count(x)
    }
    words
  end
end

#p Phrase.new("car : carpet as java : javascript!!&@$%^&").word_count
