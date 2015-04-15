class Raindrops
  def self.convert number
    divisors = [3, 5, 7]
    drops = ['Pling', 'Plang', 'Plong']
    output = divisors.zip(drops).inject('') do |out, elem|
      if (number.to_f / elem[0]) - (number.to_f / elem[0]).floor == 0
        out + elem[1]
      else
        out
      end
    end
    output.length > 0 ? (return output) : (return number.to_s)
  end
end
