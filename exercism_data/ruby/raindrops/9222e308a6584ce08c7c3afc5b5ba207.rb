class Raindrops
  DATA = {
    7 => 'Plong',
    5 => 'Plang',
    3 => 'Pling',
  }
  def self.convert(drop)
    DATA.each_with_object('') do |(num,text), acc|
      acc.prepend(text) if (drop % num).zero?
    end.tap do |ret|
      break ret.empty? ? drop.to_s : ret
    end
  end
end
