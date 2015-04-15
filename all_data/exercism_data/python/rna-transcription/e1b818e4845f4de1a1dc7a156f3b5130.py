#
# Submission file for the python rna_transcription_test exercise.
#
# v1: dict map and list processing with string join, includes error checks
#     spaced for readability purposes

# DNA to RNA map dict
map = {
        'G':'C',
        'C':'G',
        'T':'A',
        'A':'U'
      }

def to_rna(dna):
  '''
  Accept DNA string, return RNA string
  '''
  # Initialise rna list
  rna = [] 
  
  # If DNA is not False, continue
  if dna:

    # Loop through DNA, each item is called unit   
    for unit in dna:
    
      # Confirm our key exists in MAP
      if map.has_key(unit):
    
        # Append each RNA conversion from map to list
        rna.append(map[unit])
      else:
    
        # Append 'F' for failured conversion (instead of stopping)
        rna.append('F')        

  # Return RNA values as joined string
  return ''.join(rna)
