module Raindrops
  extend self

  def convert(drop)
    converter(drop,
              3 => 'Pling',
              5 => 'Plang',
              7 => 'Plong',
             )
  end

  def converter(drop, data)
    ret = data.map { |(div, text)| text if drop.modulo(div).zero? }.join
    ret.empty? ? drop.to_s : ret
  end
end
