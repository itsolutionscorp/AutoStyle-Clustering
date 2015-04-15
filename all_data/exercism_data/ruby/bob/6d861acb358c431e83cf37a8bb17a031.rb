class Bob
  def hey(incoming_remark)
    if there_is_no incoming_remark
      "Fine. Be that way!"
    elsif there_is_a_loud incoming_remark
      "Woah, chill out!"
    elsif there_is_a_questioning incoming_remark
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def there_is_no(remark)
    remark.strip.empty?
  end

  def there_is_a_loud(remark)
    remark == remark.upcase
  end

  def there_is_a_questioning(remark)
    remark.end_with? '?'
  end
end
