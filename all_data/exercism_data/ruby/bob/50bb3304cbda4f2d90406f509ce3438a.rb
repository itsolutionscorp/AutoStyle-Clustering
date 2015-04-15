class Bob
  def hey(content)
    case content
    when silence then 'Fine. Be that way.'
    when questioning then 'Sure.'
    when yelling then 'Woah, chill out!'
    else 'Whatever.'
    end
  end

  private

  def questioning
    ->(content) { content.end_with? '?' }
  end

  def yelling
    ->(content) { content == content.upcase }
  end

  def silence
    ->(content) { content.nil? or content.empty? }
  end
end
