module Raindrops

  LOOKUP = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert number
    str = ""
    LOOKUP.each {|key, s| str << s if number % key == 0}
    str.empty? ? number.to_s : str
  end
end
