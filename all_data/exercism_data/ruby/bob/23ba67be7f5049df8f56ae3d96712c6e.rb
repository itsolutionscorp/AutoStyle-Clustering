class Bob
  attr_accessor :input

  def hey input
    @input = input
    return 'Fine. Be that way!' if is_quietly_addressing?
    return 'Woah, chill out!' if is_shouting?
    return 'Sure.' if is_questioning?
    'Whatever.'
  end

  def is_shouting?
    has_at_least_one_uppercase_letter? && has_no_lowercase_letters?
  end

  def has_at_least_one_uppercase_letter?
    input =~ /[A-Z]/
  end

  def has_no_lowercase_letters?
    input !~ /[a-z]/
  end

  def is_questioning?
    ends_in_a_question_mark?
  end

  def ends_in_a_question_mark?
    input =~ /\?$/
  end

  def is_quietly_addressing?
    input.nil? || input.empty? || has_only_whitespace?
  end

  def has_only_whitespace?
    input =~ /^\s+$/
  end
end
