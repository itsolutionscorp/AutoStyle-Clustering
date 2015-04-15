class Phrase < Struct.new :phrase
  def word_count
    phrase.scan(WORDS).group_by(&:downcase).
      map(&COUNT).reduce :merge
  end
  WORDS = /\w+/
  COUNT = -> (( k, v )) {{ k => v.count }}
end
