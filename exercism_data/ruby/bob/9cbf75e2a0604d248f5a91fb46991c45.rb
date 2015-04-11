class Bob
  def hey what
    rule("Fine. Be that way!") { what.to_s.strip == "" }
    rule("Woah, chill out!")   { what.match /^([^a-z]*[A-Z]+[^a-z]*)+$/ }
    rule("Sure.")              { what.end_with? '?' }
    rule("Whatever.")          { true }
    go
  end

  private

  def rule message, &test
    (@rules ||= []) << Class.new {
      define_method(:message)  { message }
      define_method(:matches?) { test.call }
    }.new
  end

  def go
    @rules.find { |rule|
      rule.matches?
    }.message
  end
end
