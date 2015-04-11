class Anagram

  attr_reader :subject
  def initialize(subject)
    @subject = subject.downcase
  end

  def match(tests)
    tests.select { |test| anagram_of?(test) }
  end

  private

  def anagram_of?(s)
    standardize(s) == standardize(subject) && !duplicate?(s)
  end

  def standardize(s)
    s.downcase.chars.sort
  end

  def duplicate?(s)
    s.downcase == subject.downcase
  end

end
