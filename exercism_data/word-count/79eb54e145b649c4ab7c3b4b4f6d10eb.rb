class Phrase < Struct.new :phrase
  def word_count
    words.group_by(&:downcase).map(&COUNT).reduce :merge
  end
  def words; phrase.scan(WORDS) end
  WORDS = /\w+/
  COUNT = -> (( k, v )) {{ k => v.count }}
end
