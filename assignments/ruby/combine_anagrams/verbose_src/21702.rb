#a
#def combine_anagrams words
#  grouping = [[]]
#  words.each {|x| grouping.each {|y| y.empty?; y << x; break; end; y.each {|z| #if (z.downcase.chars.sort.join == x.downcase.chars.sort.join); y << x; break; e#nd;} }    }
# end
#end
def combine_anagrams words
  grouping = []

  words.each{|x| unless grouping.flatten.include? x then grouping << words.select{|y| x.downcase.chars.sort.join == y.downcase.chars.sort.join} end }
  grouping

end


arr = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
combine_anagrams arr


#, , y.each {|z| z.downcase.chars.sort.join == x.downcase.chars.sort.join ? y << x, found = true, break :  }, if(!found) y << x


