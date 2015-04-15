# A lackadaisical teenager. In conversation, his responses are very limited.
class Bob
  def hey(approach)
    you = Approach.new(approach)
    return 'Sure.' if you.ask_him_a_question?
    return 'Whatever.' if you.tell_him_something?
    return 'Woah, chill out!' if you.yell_at_him?
    return 'Fine. Be that way.' if you.dont_say_anything?
  end
end

# Examine and categorize an approach text.
class Approach
  def initialize(text)
    @text = text.to_s
  end

  def dont_say_anything?
    @text.empty?
  end

  def yell_at_him?
    @text == @text.upcase && !dont_say_anything?
  end

  def ask_him_a_question?
    @text.end_with?('?') && !yell_at_him?
  end

  def tell_him_something?
    !yell_at_him? && !dont_say_anything? && !ask_him_a_question?
  end
end
