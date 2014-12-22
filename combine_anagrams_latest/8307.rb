def combine_anagrams(words)
  i=0;
  array=Array.new
  words.each{
    |word|
    w=word.downcase.sort
    found=false
    for i in 0..array.length-1
      if(array[i][0].downcase.sort==w)
        array[i][array[i].length]=word
        found=true;
        break;
      end
    end
    if !found
      array[array.length]=[word]
    end
  }
array;
end 
class String
  def sort
    myArray = self.split(//);
    myArray.sort!
    myArray.join
  end
end
p combine_anagrams(['cars', 'for','ARCS' ,'roF','potatoes', 'racs', 'four','scar', 'creams', 'scream'])
