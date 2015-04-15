class Bob
  def hey(m)
    if m.nil? || m.empty?
      return 'Fine. Be that way.'
    end

    letters = m.split('')

    return 'Sure.' unless letters.last != "?"

    letters.each do |chr|
      if chr =~ /[a-z]/
        return 'Whatever.'
      end
    end
    return 'Woah, chill out!'
  end
end
