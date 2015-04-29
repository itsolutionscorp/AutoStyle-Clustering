class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    @word_count ||= begin
      groups = @text.scan(/[[:alnum:]']+/).group_by(&:downcase)
      Hash[ groups.map { |k, v| [k, v.count] } ]
    end
  end
end
