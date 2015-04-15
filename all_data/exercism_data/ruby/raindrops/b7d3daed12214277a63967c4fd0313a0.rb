class Raindrops
  def self.convert(num)
    droptext = ''
    mapping.keys.each do |prime|
      droptext << mapping[prime] if num % prime.to_i == 0
    end
    droptext = num.to_s if droptext == ''
  end

  def mapping
    {
      '3' => 'Pling',
      '5' => 'Plang',
      '7' => 'Plong'
    }
  end
end
