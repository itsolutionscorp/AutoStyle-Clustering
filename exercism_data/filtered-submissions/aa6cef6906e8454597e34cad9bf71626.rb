class Hamming
  class << self	
    def compute(x,y)
      xarray = x.split(//)
      yarray = y.split(//)
      distance = 0
      ham = 0

      if xarray.size > yarray.size
	      large_array = xarray
	      small_array = yarray
      else
	      large_array = yarray
	      small_array = xarray
      end
      
      small_array.each do |a| 
        ham += 1 unless a == large_array[distance]
	distance += 1
      end

      ham

    end
  end
end
