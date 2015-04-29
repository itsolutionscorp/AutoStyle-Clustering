module Raindrops

  def self.convert(num)
    str = ''
    map = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

    map.keys.each do |key|
      begin
        str.concat(map.fetch(key)) if num % key == 0
      rescue KeyError
      end
    end

    if str.empty?
      num.to_s
    else
      str
    end
  end

end
