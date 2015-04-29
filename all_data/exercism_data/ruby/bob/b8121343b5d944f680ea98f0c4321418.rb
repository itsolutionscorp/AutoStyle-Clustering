class Bob
  def initialize
    response = ""
  end

  def hey(statement)
    if statement.strip.empty?
      response = "Fine. Be that way!"
    elsif is_angry?(statement)
      response = "Whoa, chill out!"
    elsif statement[-1, 1] == "?"
      response = "Sure."
    else
      response = "Whatever."
    end
  end

  def is_angry?(statement)
    mod_state = statement.gsub(/[\d\W]/, '')
    if mod_state.empty?
      false
    elsif mod_state == mod_state.upcase
      true
    else
      false
    end
  end
end
