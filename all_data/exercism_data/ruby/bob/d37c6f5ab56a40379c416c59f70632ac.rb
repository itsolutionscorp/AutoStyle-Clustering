module ActsLikeTeenager
  def hey(str)
    handle_shout(str) || handle_question(str) || handle_silence(str) || handle_statement(str)
  end

  def handle_shout(str)
    "Woah, chill out!" if str.match(/[a-zA-Z]/) && str == str.upcase
  end

  def handle_question(str)
    "Sure." if str.match /\?\z/  
  end

  def handle_silence(str)
    "Fine. Be that way!" if str.strip == ''
  end

  def handle_statement(str)
    "Whatever."
  end
end

class Bob
  include ActsLikeTeenager
end
