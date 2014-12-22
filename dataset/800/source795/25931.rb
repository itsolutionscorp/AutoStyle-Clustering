#p = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'scream', 'creams']
#p=[]

def combine_anagrams(pt) 
  s={}
pt.each do |t|
  key=t.split('').sort.join
  s[key] ||= []
  s[key] << t
end
  #if s.values.length==0 
  #  s[[]]||=[]
  #end
  s.values
end

#tr(p)