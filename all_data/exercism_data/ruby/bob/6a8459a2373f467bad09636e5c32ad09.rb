class Bob
  def will_respond_to?(statement)
    fail NoMethodError
  end
  def hey(statement)
    fail NoMethodError
  end
end

class RelaxedBob < Bob
  def will_respond_to?(statement)
    true
  end

  def hey(statement)
    'Whatever.'
  end
end
class DefensiveBob < Bob
  def will_respond_to?(statement)
    return false if not statement or statement.empty?
    not statement.scan(/[A-Z]/).empty? and statement.scan(/[a-z]/).empty?
  end

  def hey(statement)
    'Woah, chill out!'
  end
end
class WiseBob < Bob
  def will_respond_to?(statement)
    return false if not statement or statement.empty?
    statement.strip.end_with? '?'
  end

  def hey(statement)
    'Sure.'
  end
end
class SilentBob < Bob
  def will_respond_to?(statement)
    not statement or statement.strip.empty?
  end

  def hey(statement)
    'Fine. Be that way!'
  end
end

class BobProxy

  BOB_SPECIFICATIONS = [
    DefensiveBob.new, WiseBob.new, SilentBob.new, RelaxedBob.new
  ]

  def hey(statement)
    BOB_SPECIFICATIONS.find { |bob|
      bob.will_respond_to? statement
    }.hey(statement)
  end
end
Bob = BobProxy
# vim: ts=2 sw=2
# encoding: utf8
