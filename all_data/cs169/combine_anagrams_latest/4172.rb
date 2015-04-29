an=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(an)
@an=an
i=-1
new_an=an.map {|x| [x.downcase.split('').sort.join, i+=1]}
meth(new_an)
end
def meth(arry,n=[])
	m=[]
    while arry != (arry.last || []) do
        new_arry=arry
        a=arry.last[0]
        arry.sort.each do |item|
              if item[0]==a
                m = m << @an[item[1]]
                arry.delete(item)
              end   
        end
    n=n<<m    
    meth(new_arry,n)
end
return n
end
combine_anagrams(an)

