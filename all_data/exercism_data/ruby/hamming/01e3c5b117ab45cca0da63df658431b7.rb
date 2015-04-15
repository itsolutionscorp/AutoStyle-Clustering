class Hamming
  
  class << self
    def compute(a, b)
      step = [a.length, b.length].min
      index = 0
      results = []
      while index <= a.length do 
        str1 = a[index .. step+index]
        results << compare_each_char(str1, b, step) 
        index += 1
      end
      step - results.max
    end

    private 

    def compare_each_char(str1, str2, length)
      length.times.inject(0){ |k, i| str1[i] == str2[i] ? k+=1 : k }
    end

  end

end
