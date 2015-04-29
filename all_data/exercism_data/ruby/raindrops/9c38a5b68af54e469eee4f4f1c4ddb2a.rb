module Raindrops
  def self.convert(value, x = '')
    x += 'Pling' if value % 3 == 0
    x += 'Plang' if value % 5 == 0
    x += 'Plong' if value % 7 == 0
    x = value.to_s if x.empty?
    x
  end

  def self.convert_2(value)
    if value % (3*5*7) == 0
      "PlingPlangPlong"
    elsif value % (3*5) == 0
      "PlingPlang"
    elsif value % (3*7) == 0
      "PlingPlong"
    elsif value % (5*7) == 0
      "PlangPlong"
    elsif value % 3 == 0
      "Pling"
    elsif value % 5 == 0
      "Plang"
    elsif value % 7 == 0
      "Plong"
    else
      value.to_s
    end

  end

  def self.convert_3(value)
    x = [[3,'i'],[5,'a'],[7,'o']].inject('') do |memo, item|
      (value % item.first == 0) ? memo += "Pl#{item.last}ng" : memo
    end
    x = value.to_s if x.empty?
    x
  end
end
