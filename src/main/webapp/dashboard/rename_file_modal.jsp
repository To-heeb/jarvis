 <!-- Rename Modal -->
 <div class="modal modal-top fade" id="rename_file_modal" tabindex="-1">
   <div class="modal-dialog">
     <form action="route_file" method="GET" class="modal-content">
     <input type="hidden" name='command' value='RENAME'/>
     <input type="hidden" id="file_id" name='file_id' />
       <div class="modal-header">
         <h5 class="modal-title" id="modalTopTitle">Rename File</h5>
         <button
           type="button"
           class="btn-close"
           data-bs-dismiss="modal"
           aria-label="Close"
         ></button>
       </div>
       <div class="modal-body">
         <div class="row">
           <div class="col mb-3">
             <label for="nameSlideTop" class="form-label">Name</label>
             <input
               type="text"
               id="rename_file_modal_value"
               class="form-control"
               name = "file_name"
               placeholder="Enter Name"
               autofocus
             />
           </div>
         </div>
       </div>
       <div class="modal-footer">
         <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
           Close
         </button>
         <button type="submit" class="btn btn-primary">Update</button>
       </div>
     </form>
   </div>
 </div>
 <!-- End Rename Modal -->