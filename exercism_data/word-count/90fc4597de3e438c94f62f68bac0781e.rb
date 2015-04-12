class Phrase
  def initialize text

    words = text.scan(/[\w']+/).map{|word| word.downcase }

    @word_count = Hash[words
                        .group_by{|word| word }
                        .map{|k, v| [k, v.count] } ]
  end
  attr_reader :word_count
end
