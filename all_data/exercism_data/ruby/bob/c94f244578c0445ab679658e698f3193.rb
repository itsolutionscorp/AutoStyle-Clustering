class Bob
  def hey(str)
    if there?(str)
      fine
    elsif caps?(str)
      chillout
    elsif question?(str)
      sure
    else
      whatever
    end
  end

  private

  def question?(str)
    str.end_with?('?')
  end

  def caps?(str)
    nstr = str
    str == nstr.upcase
  end

  def there?(str)
    str.to_s.strip == ""
  end

  def whatever
    answers[:whatever]cd
  end

  def chillout
    answers[:chillout]
  end

  def sure
    answers[:sure]
  end

  def fine
    answers[:fine]
  end

  def answers
    {
      :whatever => "Whatever.",
      :chillout => "Woah, chill out!",
      :sure => "Sure.",
      :fine => "Fine. Be that way!"
    }
  end
end
