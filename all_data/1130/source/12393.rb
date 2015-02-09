def value_string(string)
  counter = 0
  string.each_char do |e|
	counter+=e.ord
  end
  return counter
end

def anagram?(string1,string2)
  answer = (string1.size == string2.size)
  answer &= (value_string(string1) == value_string(string2))
  if answer then
    string2.each_char do |e|	
    	answer &= string1.include? e
    end	
  end
  return answer 
end

def combine_anagrams(list)
 limit = list.count
 i,j = 0;
 temp = "";
 listGen = Array.new

 while i < limit do
   j = i
   temp = list[i]
   listEspec = Array.new

   while j < limit do
     if !(temp.empty?) then
	 if(anagram?(temp.downcase,list[j].downcase)) then
            listEspec.push(list[j])
		list[j] = "";   
       end
     end
     j+=1
   end
   
   if !(listEspec.empty?) then   
   	listGen.push(listEspec)
   end
   i+=1
 end 

 return listGen
end