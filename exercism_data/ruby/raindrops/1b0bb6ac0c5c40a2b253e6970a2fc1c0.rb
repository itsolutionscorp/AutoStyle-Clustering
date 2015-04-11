class Raindrops

  FACTORS = [
    { factor: 3, value: 'Pling' },
    { factor: 5, value: 'Plang' },
    { factor: 7, value: 'Plong' }
  ]

  def self.convert(num)
    FACTORS.inject(nil) do |result, elem|
      if num % elem[:factor] == 0
        (result ||= '') << elem[:value]
      end
      result
    end || num.to_s
  end
end
