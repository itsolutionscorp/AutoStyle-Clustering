
def canonical(str) 
  lower = str.downcase
  
  out = ""
  lower.split('').sort.each { |s|
    out = out + s
  }
  
  out
end

def combine_anagrams(str_array) 

  grouped = Hash.new
  str_array.each { |unsorted|
    s = canonical(unsorted)
    if grouped[s] == nil then
      grouped[s] = []
    end
    grouped[s] << unsorted
  }

  grouped_arr = []
  grouped.each { |key, value|
    grouped_arr << value
  }

  grouped_arr

end
    
  
