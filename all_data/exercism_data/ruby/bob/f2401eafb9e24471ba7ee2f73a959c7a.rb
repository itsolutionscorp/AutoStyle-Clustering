class Bob
  def hey(remark)
    return 'Fine. Be that way!' if remark.match /\A\s*\z/

    words = remark.scan(/\w+/).select { |word| word.match /[A-Za-z]+/ }
    if words.any? && words.all? { |word| word.match /[A-Z]+/ }
      'Whoa, chill out!'
    elsif remark[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
