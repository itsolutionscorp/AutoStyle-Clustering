class Prime
  require 'prime'

  def self.nth(number)
    if number < 1
      raise ArgumentError
    else
      answer = find_prime(number)[number - 1]
    end
  end

  def self.find_prime(x)
    list  = [2, 3]
    index = list.last
    while list.length <= x
      if index.prime?
        list << index unless list.include? index
        index += 2
      else
        index += 2
      end
    end
    list
  end

end
