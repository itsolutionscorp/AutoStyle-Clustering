class Raindrops
  def self.convert number
    divisors = [3, 5, 7]
    drops = ['Pling', 'Plang', 'Plong']
    output = divisors.zip(drops).inject('') do |out, elem|
      out += elem[1] if (number % elem[0]) == 0
      out
    end
    output.empty? ? (return number.to_s) : (return output)
  end
end
