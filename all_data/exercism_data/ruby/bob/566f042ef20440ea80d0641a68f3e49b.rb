class Bob
  def hey(remark)
    case remark
    when not_spoken_to
      'Fine. Be that way!'
    when yelled_at
      'Whoa, chill out!'
    when questioned
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def not_spoken_to
    ->(remark) { remark.strip.empty? }
  end

  def yelled_at
    ->(remark) { remark == remark.upcase && remark.match(/[^\W\d_]/) }
  end

  def questioned
    ->(remark) { remark.end_with? '?' }
  end
end
