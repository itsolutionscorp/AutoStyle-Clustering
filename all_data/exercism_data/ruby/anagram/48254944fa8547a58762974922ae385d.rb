class Anagram

  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(word_array)
    word_array.reject! { |word| granulate(word) != granulate(subject) }
    word_array.reject { |word| word.downcase == subject.downcase }
  end

  private
    def granulate(input)
      input.downcase.split(//).sort
    end

end
