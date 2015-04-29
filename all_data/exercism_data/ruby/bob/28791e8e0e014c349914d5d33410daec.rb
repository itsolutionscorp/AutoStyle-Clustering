class Bob
  def hey(str)
    if there?(str)
      answers[:fine]
    elsif caps?(str)
      answers[:chillout]
    elsif question?(str)
      answers[:sure]
    else
      answers[:whatever]
    end
  end

  private

  def question?(str)
    str.end_with?('?')
  end

  def caps?(str)
    str == str.upcase
  end

  def there?(str)
    str.to_s.strip.empty?
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
