class Bob
  def hey greeting
    greeting_type = determine_greeting_type(greeting)
    respond_to greeting_type
  end

  private

  def determine_greeting_type greeting
    GreetingClassifier.new(greeting).type
  end

  def respond_to greeting_type
    case greeting_type
    when :silence then "Fine. Be that way!"
    when :yelling then "Woah, chill out!"
    when :question then "Sure."
    when :other then "Whatever."
    end
  end
end

class GreetingClassifier
  TYPES = %w(silence yelling question)

  def initialize greeting
    @greeting = greeting
  end

  def type
    (find_type || :other).to_sym
  end

  private
  attr_reader :greeting

  def find_type
    TYPES.detect { |type| send("#{type}?".to_sym) }
  end

  def silence?
    greeting.strip == ""
  end

  def yelling?
    greeting.upcase == greeting
  end

  def question?
    greeting.slice(-1) == "?"
  end
end
