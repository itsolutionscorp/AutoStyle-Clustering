class Phrase
  def initialize(word)
    @words ||= word.gsub(',', ' ').downcase.split.map{ |var| var.tr("^A-Za-z0-9'", ' ').strip }
    @words.delete('')
  end

  def word_count
    @words.inject({})  { |_, var| _[var] = _[var].nil? ? 1 : _[var] + 1; _ }
  end
end
