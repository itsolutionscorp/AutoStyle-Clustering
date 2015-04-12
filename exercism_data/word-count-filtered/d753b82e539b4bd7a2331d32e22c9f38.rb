class Phrase
  attr_reader :sentence

  def initialize(sentence)
   @sentence = sentence 
  end

  def word_count
    words = sentence.tr(".,!!&@$%^&:", " ").downcase.split(" ").group_by { |x| x }
    Hash[words.map{|k,v| [k,v.count] } ]
  end
end
