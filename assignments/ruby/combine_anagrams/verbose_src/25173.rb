def combine_anagrams(my_array)
  @dati=[]
  @cinder={}
  my_array.each { |word|
      rearr=word.downcase.split("").sort.join("")
      late=@cinder.fetch(rearr, '')
      if (late=='') then
          @cinder[rearr]=[word]
      else
          @cinder[rearr]=late<<word
      end
  }

@cinder.each_value {| value | 
  @dati<<value
}
p @dati

end 
