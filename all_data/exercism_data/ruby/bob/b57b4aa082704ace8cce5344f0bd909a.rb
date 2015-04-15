class Bob 
  def hey string
    return 'Fine. Be that way!' if string.gsub(/\s+/, "").empty?
    return 'Woah, chill out!' if string.gsub(/[^0-9a-z ]/i, '').split(/ /).group_by(&:size).max.last[-1].gsub('?','').gsub(/[A-Z1]/,"").empty?
    return 'Sure.' if string[-1].eql? '?'
    return 'Whatever.'
  end
end
